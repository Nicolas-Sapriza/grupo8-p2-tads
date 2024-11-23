import { validateInput } from '@/helpers/validateInput';
import { verifyEmailWithNeverBounce } from '@/helpers/verifyEmail';
import { EmailsService } from '@/services/emailService';
import { fetchEmailFromFindyMail, fetchEmailFromProspeo, fetchEmailFromLeadMagic } from '@/services/providerService';

const emailsService = new EmailsService();

export class EmailsController {
    async fetchEmail(fullName: string, companyDomain: string) {
        // Validar parámetros
        validateInput(fullName, 'string', 'Invalid name');
        validateInput(companyDomain, 'string', 'Invalid company domain');

        // Verificar si el contacto ya está en la base de datos
        const contact = await emailsService.filterByDate(fullName, companyDomain);
        if (contact) {
            return { email: contact.email };
        }

        // Proveedores
        const providers = [
            fetchEmailFromProspeo, 
            fetchEmailFromLeadMagic,
            //fetchEmailFromHunter,
            fetchEmailFromFindyMail,
            //fetchEmailFromDatagma,
        ];

        // Intentar encontrar un email válido
        let valid = false;
        for (const providerFunc of providers) {
            console.log('Llamando a', providerFunc.name);
            const email = await providerFunc(fullName, companyDomain);
            console.log('Email encontrado:', email, 'con', providerFunc.name);
            if (email) {
                valid = await verifyEmailWithNeverBounce(email, providerFunc.name);

                if (valid) {
                    // Crear contacto en la base de datos
                    await emailsService.createContact(fullName, companyDomain, email, providerFunc.name);
                }

                // Registrar la llamada al proveedor
                await emailsService.createLogApiCall(providerFunc.name, fullName, companyDomain, email, valid);

                if (valid) {
                    return { email };
                }
            }
        }

        // Si no se encuentra un email válido
        throw new Error('Email not found');
    }
}
