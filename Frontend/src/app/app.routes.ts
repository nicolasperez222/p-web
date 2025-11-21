import { Routes } from '@angular/router';
import { ClienteListComponent } from './cliente-list/cliente-list.component';
import { ClienteFormComponent } from './cliente-form/cliente-form';
import { ResolucionFormComponent } from './resolucion-form/resolucion-form';
import { ResolucionListComponent } from './resolucion-list/resolucion-list';
import { DocumentoEmitidoFormComponent } from './documento-emitido-form/documento-emitido-form.component';
import { DocumentoEmitidoListComponent } from './documento-emitido-list/documento-emitido-list.component';
import { HomeComponent } from './home/home';
import { LoginComponent } from './security/login/login.component';
import { authGuard } from './guards/auth.guard';


export const routes: Routes = [
  { path: 'home', component: HomeComponent, canActivate: [authGuard] },
  { path: 'login', component: LoginComponent },
  { path: '', pathMatch: 'full', redirectTo: 'login' },
  { path: 'lista-clientes', component: ClienteListComponent, canActivate: [authGuard] },
  { path: 'form-clientes', component: ClienteFormComponent, canActivate: [authGuard] },
  { path: 'lista-resoluciones', component: ResolucionListComponent, canActivate: [authGuard] },
  { path: 'form-resoluciones', component: ResolucionFormComponent, canActivate: [authGuard] },
  { path: 'lista-facturas', component: DocumentoEmitidoListComponent, canActivate: [authGuard] },
  { path: 'form-facturas', component: DocumentoEmitidoFormComponent, canActivate: [authGuard] },
  { path: '**', redirectTo: '' }
];
