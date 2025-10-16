const API_CLIENTES = "/api/clientes";
const API_TIPOS_DOC = "/api/tipos-documento";
const API_RESOLUCIONES = "/api/resoluciones";

async function cargarClientes() {
    const res = await fetch(API_CLIENTES);
    const data = await res.json();
    const select = document.getElementById("cliente");
    data.forEach(c => {
        const opt = document.createElement("option");
        opt.value = c.id;
        opt.textContent = c.razonSocial;
        select.appendChild(opt);
    });
}

async function cargarTiposDocumento() {
    const res = await fetch(API_TIPOS_DOC);
    const data = await res.json();
    const select = document.getElementById("tipoDocumento");
    data.forEach(td => {
        const opt = document.createElement("option");
        opt.value = td.idTipoDocumento;
        opt.textContent = td.nombre;
        select.appendChild(opt);
    });
}

async function cargarResoluciones() {
    const res = await fetch(API_RESOLUCIONES);
    const data = await res.json();
    const tbody = document.getElementById("resolucionesTableBody");
    tbody.innerHTML = "";

    data.forEach(r => {
        const tr = document.createElement("tr");
        tr.innerHTML = `
            <td>${r.idResolucion}</td>
            <td>${r.cliente?.razonSocial || ""}</td>
            <td>${r.tipoDocumento?.nombre || ""}</td>
            <td>${r.numeroResolucion}</td>
            <td>${r.prefijo || ""}</td>
            <td>${r.fechaCreacion || ""}</td>
            <td>${r.desde}</td>
            <td>${r.hasta}</td>
            <td>${r.fechaDesde} - ${r.fechaHasta}</td>
            <td>
                <button onclick="eliminarResolucion(${r.idResolucion})">Eliminar</button>
            </td>
        `;
        tbody.appendChild(tr);
    });
}

async function guardarResolucion(e) {
    e.preventDefault();

    const dto = {
        cliente: { id: document.getElementById("cliente").value },
        tipoDocumento: { idTipoDocumento: document.getElementById("tipoDocumento").value },
        numeroResolucion: document.getElementById("numeroResolucion").value,
        prefijo: document.getElementById("prefijo").value,
        fechaCreacion: document.getElementById("fechaCreacion").value,
        llaveTecnica: document.getElementById("llaveTecnica").value,
        desde: document.getElementById("desde").value,
        hasta: document.getElementById("hasta").value,
        fechaDesde: document.getElementById("fechaDesde").value,
        fechaHasta: document.getElementById("fechaHasta").value
    };
     console.log("DTO a enviar:", dto);
    await fetch(API_RESOLUCIONES, {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(dto)
    });

    document.getElementById("formResolucion").reset();
    cargarResoluciones();
}

async function eliminarResolucion(id) {
    if (!confirm("¿Seguro que quieres eliminar esta resolución?")) return;
    await fetch(`${API_RESOLUCIONES}/${id}`, { method: "DELETE" });
    cargarResoluciones();
}

document.getElementById("formResolucion").addEventListener("submit", guardarResolucion);

cargarClientes();
cargarTiposDocumento();
cargarResoluciones();
