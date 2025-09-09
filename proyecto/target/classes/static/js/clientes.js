
const API_CLIENTES = "/api/clientes";
const API_DEPARTAMENTOS = "/api/departamentos";
const API_MUNICIPIOS = "/api/municipios";
const API_TIPO_EMPRESA = "/api/tipo-empresa";

const form = document.getElementById("formCliente");
const tableBody = document.getElementById("clientesTableBody");

const departamentoSelect = document.getElementById("departamento");
const municipioSelect = document.getElementById("municipio");
const tipoEmpresaSelect = document.getElementById("tipoEmpresa");

let logoBase64 = "";


document.addEventListener("DOMContentLoaded", async () => {
    await cargarSelects();
    await cargarClientes();
});


form.addEventListener("submit", async (e) => {
    e.preventDefault();

    const cliente = {
        representanteLegal: document.getElementById("representanteLegal").value,
        nit: document.getElementById("nit").value,
        dv: document.getElementById("dv").value,
        razonSocial: document.getElementById("razonSocial").value,
        tipoEmpresa: parseInt(tipoEmpresaSelect.value),
        responsabilidadTributaria: document.getElementById("responsabilidadTributaria").value,
        regimenIva: document.getElementById("regimenIva").value,
        direccion: document.getElementById("direccion").value,
        email: document.getElementById("email").value,
        telefono: document.getElementById("telefono").value,
        codigoCiiu: document.getElementById("codigoCiiu").value,
        impuesto: document.getElementById("impuesto").value,
        estado: parseInt(document.getElementById("estado").value),
        departamento: { id: parseInt(departamentoSelect.value) },
        municipio: { id: parseInt(municipioSelect.value) },
        logo: logoBase64
    };

    try {
        const res = await fetch(API_CLIENTES, {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify(cliente)
        });

        const msg = await res.text();
        alert(msg);

        form.reset();
        logoBase64 = "";
        await cargarClientes();
    } catch (err) {
        console.error("Error al guardar cliente:", err);
    }
});


async function cargarClientes() {
    tableBody.innerHTML = "";

    try {
        const res = await fetch(API_CLIENTES);
        const clientes = await res.json();

        clientes.forEach(c => {
            const tr = document.createElement("tr");

            tr.innerHTML = `
                <td>${c.id}</td>
                <td>${c.nit}-${c.dv}</td>
                <td>${c.razonSocial}</td>
                <td>${c.email || ""}</td>
                <td>${c.telefono || ""}</td>
                <td>${c.municipio ? c.municipio.nombre : ""}</td>
                <td>
                    <button onclick="eliminarCliente(${c.id})">Eliminar</button>
                </td>
            `;

            tableBody.appendChild(tr);
        });
    } catch (err) {
        console.error("Error al cargar clientes:", err);
    }
}


async function eliminarCliente(id) {
    if (!confirm("¿Seguro de eliminar este cliente?")) return;

    try {
        const res = await fetch(`${API_CLIENTES}/${id}`, { method: "DELETE" });

        if (res.status === 204) {
            alert("Cliente eliminado");
            await cargarClientes();
        } else {
            alert("No se pudo eliminar el cliente");
        }
    } catch (err) {
        console.error("Error al eliminar cliente:", err);
    }
}


async function cargarSelects() {
    await cargarDepartamentos();
    await cargarMunicipios();
    await cargarTiposEmpresa();
}

async function cargarDepartamentos() {
    try {
        const res = await fetch(API_DEPARTAMENTOS);
        const departamentos = await res.json();

        departamentoSelect.innerHTML = "<option value=''>Seleccione</option>";
        departamentos.forEach(d => {
            const opt = document.createElement("option");
            opt.value = d.id;
            opt.textContent = d.nombre;
            departamentoSelect.appendChild(opt);
        });
    } catch (err) {
        console.error("Error al cargar departamentos:", err);
    }
}

async function cargarMunicipios() {
    try {
        const res = await fetch(API_MUNICIPIOS);
        const municipios = await res.json();

        municipioSelect.innerHTML = "<option value=''>Seleccione</option>";
        municipios.forEach(m => {
            const opt = document.createElement("option");
            opt.value = m.id;
            opt.textContent = m.nombre;
            municipioSelect.appendChild(opt);
        });
    } catch (err) {
        console.error("Error al cargar municipios:", err);
    }
}

async function cargarTiposEmpresa() {
    try {
        const res = await fetch(API_TIPO_EMPRESA);
        const tipos = await res.json();

        tipoEmpresaSelect.innerHTML = "<option value=''>Seleccione</option>";
        tipos.forEach(t => {
            const opt = document.createElement("option");
            opt.value = t.id;
            opt.textContent = t.nombre;
            tipoEmpresaSelect.appendChild(opt);
        });
    } catch (err) {
        console.error("Error al cargar tipos de empresa:", err);
    }
}


document.getElementById("logo").addEventListener("change", function () {
    const file = this.files[0];
    if (!file) return;

    const reader = new FileReader();
    reader.onloadend = () => {
        logoBase64 = reader.result; 
    };
    reader.readAsDataURL(file);
});