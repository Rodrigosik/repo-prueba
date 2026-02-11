export const parseFechaYYYYMMDDToDate = (fecha: string): Date | null => {
  if (!/^\d{4}-\d{2}-\d{2}$/.test(fecha)) return null;
  const [year, month, day] = fecha.split('-').map(Number);
  // Validación básica de fecha
  if (
    isNaN(year) ||
    isNaN(month) ||
    isNaN(day) ||
    month < 1 ||
    month > 12 ||
    day < 1 ||
    day > 31
  ) {
    return null;
  }
  return new Date(year, month - 1, day);
};
