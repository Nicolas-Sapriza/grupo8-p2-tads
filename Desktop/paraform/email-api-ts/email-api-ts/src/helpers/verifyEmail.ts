import axios from 'axios';

// Define el tipo para la respuesta de NeverBounce
type NeverBounceResponse = {
    status: string;
    result: string;
    flags: string[];
    suggested_correction: string;
    retry_token: string;
    execution_time: number;
};

export const verifyEmailWithNeverBounce = async (email: string, name:string): Promise<boolean> => {
    console.log(`Verificando email de ${name} con NeverBounce`);
    const apiKey = process.env.NEVERBOUNCE_API_KEY;

    if (!apiKey) {
        console.error('NEVERBOUNCE_API_KEY no está configurado en el entorno');
        return false;
    }

    try {
        const url = `https://api.neverbounce.com/v4/single/check?key=${apiKey}&email=${encodeURIComponent(email)}`;

        // Realiza la solicitud POST a NeverBounce y tipa la respuesta
        const response = await axios.post<NeverBounceResponse>(url);

        console.log('NeverBounce response:', response.data);

        // Verifica si el resultado es válido
        if (response.data.status === 'success' && response.data.result === 'valid') {
            return true;
        }

        return false; // Si el correo no es válido
    } catch (error: unknown) {
        if (error instanceof Error) {
            console.error(`Error al verificar el email: ${error.message}`);
        } else {
            console.error('Error al verificar el email:', error);
        }
        return false; // Devuelve null si ocurre un error
    }
};
