import { PrismaClient } from '@prisma/client';
import { getName, getLastName } from '@/helpers/nameParser';

const prisma = new PrismaClient();

export class EmailsService {

    // Crea un nuevo contacto en la base de datos
    async createContact(fullName: string, companyDomain: string, email: string, sourceProvider: string) {
        try {
            const newContact = await prisma.contacts.create({
                data: {
                    firstName: getName(fullName),
                    lastName: getLastName(fullName),
                    email: email,
                    companyDomain: companyDomain,
                    sourceProvider: sourceProvider,
                    dateAdded: new Date(),
                },
            });
            return newContact;
        } catch (error: unknown) {
            console.error('Error al crear contacto:', (error as Error).message);
            throw new Error('Error al crear contacto');
        }
    }

    // Crea un registro de la llamada a la API en la base de datos
    async createLogApiCall(providerName: string, fullName: string, companyDomain: string, response: string, valid: boolean) {
        try {
            const newLog = await prisma.logs.create({
                data: {
                    providerName: providerName,
                    fullName: fullName,
                    companyDomain: companyDomain,
                    response: response,
                    valid: valid,
                },
            });
            return newLog;
        } catch (error: unknown) {
            console.error('Error al crear log de la llamada a la API:', (error as Error).message);
            throw new Error('Error al crear log');
        }
    }


    async filterByDate(fullName: string, companyDomain: string) {
        const oneMonthAgo = new Date();
        oneMonthAgo.setDate(oneMonthAgo.getDate() - 30);

        // Busca el contacto en la base de datos
        const contact = await prisma.contacts.findFirst({
            where: {
                firstName: getName(fullName),
                lastName: getLastName(fullName),
                companyDomain: companyDomain,
            },
        });

        if (contact) {
            // Verifica si el contacto fue añadido hace menos de un mes
            if (contact.dateAdded > oneMonthAgo) {
                return contact;
            } else {
                // Si el contacto es viejo, elimínalo
                await prisma.contacts.delete({
                    where: { id: contact.id },
                });
            }
        }

        return null; // Si no hay contacto válido o fue eliminado
    }
}
