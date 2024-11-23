// Extrae el primer nombre de un nombre completo
export const getName = (fullName: string): string => {
    return fullName.split(' ')[0];
};

// Extrae el apellido(s) de un nombre completo
export const getLastName = (fullName: string): string => {
    const nameParts = fullName.split(' ');
    return nameParts.slice(1).join(' ');
};
