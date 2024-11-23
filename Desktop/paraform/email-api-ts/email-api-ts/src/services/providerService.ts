import axios from 'axios';

// Helpers para obtener nombres
import { getName, getLastName } from '@/helpers/nameParser';
import { ProspeoResponse, LeadMagicResponse, HunterResponse, FindyMailResponse, DatagmaResponse } from '@/interfaces/interfaces';


// Función para llamar a Prospeo
export const fetchEmailFromProspeo = async (fullName: string, companyDomain: string): Promise<string | null> => {
    const url = 'https://api.prospeo.io/email-finder';

    try {
        const response = await axios.post(
            url,
            {
                full_name: fullName,
                company: companyDomain,
            },
            {
                headers: {
                    "Content-Type": "application/json",
                    "X-KEY": process.env.PROSPEO_API_KEY,
                  },
            }
        );

        if (response.status === 200) {
            const data = response.data as ProspeoResponse;
            return data.response.email || null;
        }
    } catch (error: any) {
        console.error('Error Prospeo:', error.message, error.response.data);
    }

    return null;
};

// Función para llamar a Leadmagic
export const fetchEmailFromLeadMagic = async (fullName: string, companyDomain: string): Promise<string | null> => {
    const url = 'https://api.leadmagic.io/email-finder';

    try {
        const response = await axios.post(
            url,
            {
                first_name: getName(fullName),
                last_name: getLastName(fullName),
                domain: companyDomain,
            },
            {
                headers: {
                    accept: 'application/json',
                    'content-type': 'application/json',
                    "X-API-Key": process.env.LEADMAGIC_API_KEY,
                },
            }
        );

        if (response.status === 200) {
            const data = response.data as LeadMagicResponse;

            console.log('data de coca:', data);

            if (data.status != 'not_found') {
                return data.email!;
            } else {
                return null;
            }
        }
    } catch (error: any) {
        console.error('Error LeadMagic:', error.message, error.response.data);
    }

    return null;
};


// Función para llamar a Hunter
export const fetchEmailFromHunter = async (fullName: string, companyDomain: string): Promise<string | null> => {
    const url = 'https://api.hunter.io/v2/email-finder';

    try {
        const response = await axios.get(url, {
            params: {
                full_name: fullName,
                domain: companyDomain,
            },
            headers: {
                Authorization: `Bearer ${process.env.HUNTER_API_KEY}`,
            },
        });

        if (response.status === 200) {
            const data = response.data as HunterResponse;
            return data.data.email
        }
    } catch (error: any) {
        console.error('Error al llamar a Hunter:', error.message);
    }

    return null;
};



export const fetchEmailFromFindyMail = async (fullName: string, companyDomain: string): Promise<string | null> => {
    const url = 'https://app.findymail.com/api/search/name';

    if (!process.env.FINDYMAIL_API_KEY) {
        throw new Error('FINDYMAIL_API_KEY no está configurada.');
    }

    try {
        // Configuración de payload y headers
        const payload = {
            name: fullName,
            domain: companyDomain,
        };

        const headers = {
            Authorization: `Bearer ${process.env.FINDYMAIL_API_KEY}`,
            'Content-Type': 'application/json',
            Accept: 'application/json',
        };

        // Hacer la solicitud
        const response = await axios.post(url, payload, { headers });

        if (response.status === 200) {
            const data = response.data as FindyMailResponse;

            if (data.error) {
                throw new Error(data.error); // Lanza un error o maneja el caso de forma adecuada
            }

            if (data.contact && data.contact.email) {
                return data.contact.email;
            }
        }
    } catch (error: any) {
        console.error('Error al llamar a FindyMail:', error.message);
        if (error.response) {
            console.error('Detalles del error:', error.response.data);
        }
    }

    return null;
};


// Función para llamar a Datagma
export const fetchEmailFromDatagma = async (fullName: string, companyDomain: string): Promise<string | null> => {
    const url = 'https://gateway.datagma.net/api/ingress/v8/findEmail';

    try {
        const response = await axios.get(url, {
            headers: {
                accept: 'application/json',
            },
            params: {
                apiId: process.env.DATAGMA_API_KEY,
                fullName,
                company: companyDomain,
            },
        });

        if (response.status === 200) {
            const data = response.data as DatagmaResponse;
            return data.email;
        }
    } catch (error: any) {
        console.error('Error al llamar a Datagma:', error.message);
    }

    return null;
};
