import { NgModule } from "@angular/core";
import { Routes, RouterModule } from "@angular/router";

//myrouting
import { EquipegetallComponent } from "./views/admin/Equipe/getallequipe.component";
import { CreateEquipeComponent } from "./views/admin/Equipe/createequipe.component";
import {UpdateEquipeComponent} from "./views/admin/Equipe/updateequipe.component";
import { CreateMembreComponent } from "./views/admin/Membre/createmembre.component";
import { MembregetallComponent } from "./views/admin/Membre/getallmembre.component";
import {UpdateMembreComponent} from "./views/admin/Membre/updatemembre.component";
//import {CalendarComponent} from "./views/admin/Membre/calendar.component";

// layouts
import { AdminComponent } from "./layouts/admin/admin.component";
import { AuthComponent } from "./layouts/auth/auth.component";

// admin views
import { DashboardComponent } from "./views/admin/dashboard/dashboard.component";
import { MapsComponent } from "./views/admin/maps/maps.component";
import { SettingsComponent } from "./views/admin/settings/settings.component";
import { TablesComponent } from "./views/admin/tables/tables.component";

// auth views
import { LoginComponent } from "./views/auth/login/login.component";
import { RegisterComponent } from "./views/auth/register/register.component";

// no layouts views
import { IndexComponent } from "./views/index/index.component";
import { LandingComponent } from "./views/landing/landing.component";
import { ProfileComponent } from "./views/profile/profile.component";

const routes: Routes = [
  // admin views
  {
    path: "admin",
    component: AdminComponent,
    children: [
      { path: "dashboard", component: DashboardComponent },
      { path: "settings", component: SettingsComponent },
      { path: "tables", component: TablesComponent },
      { path: "maps", component: MapsComponent },
      { path: "listequipe", component: EquipegetallComponent },
      { path: "addequipe", component: CreateEquipeComponent },
      { path: "updateequipe/:id", component: UpdateEquipeComponent },
      { path: "updatemembre/:id", component: UpdateMembreComponent },
      { path: "addmembre", component: CreateMembreComponent },
      { path: "listemembre", component: MembregetallComponent },
      //{ path: "calendar", component: CalendarComponent },
      { path: "", redirectTo: "dashboard", pathMatch: "full" },
    ],
  },
  // auth views
  {
    path: "auth",
    component: AuthComponent,
    children: [
      { path: "login", component: LoginComponent },
      { path: "", component: RegisterComponent },
      { path: "*", redirectTo: "", pathMatch: "full" },
    ],
  },
  // no layout views
  { path: "profile", component: ProfileComponent },
  { path: "", component: RegisterComponent },
  { path: "landing", component: LandingComponent },
  { path: "index", component: IndexComponent },
  { path: "*", redirectTo: "", pathMatch: "full" },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
