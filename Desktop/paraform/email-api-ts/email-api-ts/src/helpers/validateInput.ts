
export const validateInput = (value: string | number | boolean, type: string, errorMessage: string) => {
    if (typeof value !== type) {
        throw new Error(errorMessage);
    }
};
