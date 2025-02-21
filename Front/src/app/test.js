
const apiUrl = 'http://localhost:3000/api/estudiantes';
fetch(apiUrl)
  .then(response => {
    if (!response.ok) {
      throw new Error('Error en la red: ' + response.statusText);
    }
    return response.json(); // Parsear la respuesta a JSON
  })
  .then(data => {
    console.log('Datos recibidos:', data); // Imprimir los datos recibidos
  })
  .catch(error => {
    console.error('Error al hacer la solicitud:', error); // Manejar errores
  });
