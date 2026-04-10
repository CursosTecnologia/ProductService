async function buscar() {
    const nombre = document.getElementById('inputNombre').value;
    const tabla = document.getElementById('tablaCuerpo');

    // Limpiar tabla antes de buscar
    tabla.innerHTML = '<tr><td colspan="2">Cargando...</td></tr>';

    console.log("El nombre es:", nombre);
    try {
        // Llamada a tu microservicio
        const response = await fetch(`/products/listar?name=${nombre}`);
        const productos = await response.json();

        tabla.innerHTML = ''; // Limpiar mensaje de carga

        if (productos.length === 0) {
            tabla.innerHTML = '<tr><td colspan="2" class="text-center">No hay resultados</td></tr>';
            return;
        }


        // Cambia esto en tu bucle forEach
        productos.forEach(p => {
            const fila = `<tr>
                            <td>${p.idProduct || 'N/A'}</td>
                            <td>${p.nameProduct}</td>
                            <td>${p.descriptionProduct}</td>
                            <td>${p.priceUnidProduct}</td>
                          </tr>`;
            tabla.innerHTML += fila;
        });

    } catch (error) {
        console.error("Error al obtener productos:", error);
        tabla.innerHTML = '<tr><td colspan="2" class="text-danger">Error de conexión</td></tr>';
    }
}

async function agregarProducto() {
    // 1. Capturar valores de los inputs
    const nombre = document.getElementById('newName').value;
    const descripcion = document.getElementById('newDesc').value;
    const precio = document.getElementById('newPrice').value;

    // Validar que no estén vacíos
    if (!nombre || !descripcion || !precio) {
        alert("Por favor, completa todos los campos");
        return;
    }

    try {
        // 2. Configurar los parámetros para la URL
        const params = new URLSearchParams();
        params.append('nameProduct', nombre);
        params.append('descriptionProduct', descripcion);
        params.append('priceUnidProduct', precio);

        // 3. Hacer la petición POST
        const response = await fetch('/products', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/x-www-form-urlencoded'
            },
            body: params
        });

        if (response.ok) {
            alert("¡Producto agregado con éxito!");
            // Limpiar campos
            document.getElementById('newName').value = '';
            document.getElementById('newDesc').value = '';
            document.getElementById('newPrice').value = '';

            // Opcional: Recargar la tabla automáticamente
            buscar();
            cargarTodos();
        } else {
            alert("Error al guardar el producto");
        }

    } catch (error) {
        console.error("Error:", error);
        alert("No se pudo conectar con el servidor");
    }
}

// --- FUNCIÓN PARA LISTAR TODO ---
async function cargarTodos() {
    const tabla = document.getElementById('tablaCuerpo');

    try {
        const response = await fetch('/products/listarTodo');
        const productos = await response.json();

        tabla.innerHTML = ''; // Limpiar la tabla antes de llenar

        productos.forEach(p => {
            const fila = `<tr>
                            <td>${p.idProduct || 'N/A'}</td>
                            <td>${p.nameProduct}</td>
                            <td>${p.descriptionProduct}</td>
                            <td>$${p.priceUnidProduct}</td>
                          </tr>`;
            tabla.innerHTML += fila;
        });
    } catch (error) {
        console.error("Error al listar productos:", error);
    }
}