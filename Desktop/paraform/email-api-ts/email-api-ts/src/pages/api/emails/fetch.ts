import { NextApiRequest, NextApiResponse } from 'next';
import { EmailsController } from '@/controllers/emailsController';

const emailsController = new EmailsController();

export default async function handler(req: NextApiRequest, res: NextApiResponse) {
    if (req.method === 'POST') {
        try {
            // Extraer parámetros del cuerpo (Body)
            const { full_name, company_domain } = req.body;

            if (!full_name || !company_domain) {
                return res.status(400).json({ error: 'Faltan los parámetros requeridos: full_name y company_domain' });
            }

            // Llamar al controlador
            const result = await emailsController.fetchEmail(full_name, company_domain);
            return res.status(200).json(result);
        } catch (error: any) {
            if (error.message === 'Email not found') {
                return res.status(404).json({ error: error.message });
            }
            return res.status(500).json({ error: 'Internal server error' });
        }
    } else {
        res.setHeader('Allow', ['POST']);
        return res.status(405).json({ error: `Method ${req.method} not allowed` });
    }
}
