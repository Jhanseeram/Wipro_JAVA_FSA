const dateString = '2030-08-13';
const date = new Date(dateString);
const dayOfWeek = date.toLocaleDateString({ weekday: 'long' });
console.log(`13th August 2030 falls on a ${dayOfWeek}.`);