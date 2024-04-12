import { SafeUrl } from '@angular/platform-browser';
import { Membre } from './Membre';
import { Disponibilite } from './Disponibilite';

export interface Equipe {
  idEquipe?: number;
  nomEquipe: string;
  description: string;
  disponibilite?: Disponibilite;
  membres?: (Membre | SafeUrl)[];
}
