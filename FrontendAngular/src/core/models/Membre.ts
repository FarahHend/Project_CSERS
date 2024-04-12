import { SafeUrl } from '@angular/platform-browser';
import { Equipe } from './Equipe';

export interface Membre {
    idMembre: number;
    nom: string;
    prenom: string;
    imageFile: File;
    email: string;
    poste: string;
    number: string;
    competencesTechniques: string;
    certifications: string;
    experience: number;
    equipeInterventionId: number;
    
}
