import { Routes } from '@angular/router';
import { ClienteListComponent } from './cliente-list/cliente-list.component';
import { ClienteFormComponent } from './cliente-form/cliente-form';
import { ResolucionFormComponent } from './resolucion-form/resolucion-form';
import { ResolucionListComponent } from './resolucion-list/resolucion-list';
import { DocumentoEmitidoFormComponent } from './documento-emitido-form/documento-emitido-form.component';
import { DocumentoEmitidoListComponent } from './documento-emitido-list/documento-emitido-list.component';
import { HomeComponent } from './home/home';


export const routes: Routes = [
  { path: '', component: HomeComponent }, 
  { path: 'lista-clientes', component: ClienteListComponent },
  { path: 'form-clientes', component: ClienteFormComponent },
  { path: 'lista-resoluciones', component: ResolucionListComponent },
  { path: 'form-resoluciones', component: ResolucionFormComponent },
  { path: 'lista-facturas', component: DocumentoEmitidoListComponent },
  { path: 'form-facturas', component: DocumentoEmitidoFormComponent },
  { path: '**', redirectTo: '' } 
];
