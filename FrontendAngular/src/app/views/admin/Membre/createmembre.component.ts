import { Component, OnInit, ViewChild, ElementRef } from "@angular/core";
import { createPopper } from "@popperjs/core";
import { EquipeService } from '../../../../core/service/EquipeService';
import { MembreService } from "../../../../core/service/MembreService";
import { Membre } from "../../../../core/models/Membre";

@Component({
  selector: 'app-create-membre',
  templateUrl: './createmembre.component.html'
})
export class CreateMembreComponent implements OnInit {
  showAlert = false; 
  dropdownPopoverShow = false;
  @ViewChild("btnDropdownRef", { static: false }) btnDropdownRef!: ElementRef;
  @ViewChild("popoverDropdownRef", { static: false }) popoverDropdownRef!: ElementRef;
  equipeNames: string[] = [];
  selectedEquipeName: string = ''; 
  membre: Membre = {
    idMembre: 0,
    nom: '',
    prenom: '',
    imageFile: null,
    email: '',
    poste: '',
    number: '',
    competencesTechniques: '',
    certifications: '',
    experience: 0,
    equipeInterventionId: 0 
  };

  constructor(private equipeService: EquipeService, private membreService: MembreService) {}

  ngOnInit(): void {
    this.getEquipeNames();
  }

  getEquipeNames(): void {
    this.equipeService.getAllEquipeNames().subscribe(
      (names: string[]) => {
        this.equipeNames = names;
      },
      (error: any) => {
        console.error('Error fetching equipe names:', error);
      }
    );
  }

  toggleDropdown(event: Event): void {
    event.preventDefault();
    this.dropdownPopoverShow = !this.dropdownPopoverShow;
    if (this.dropdownPopoverShow) {
      this.createPopper();
    }
  }

  createPopper(): void {
    createPopper(
      this.btnDropdownRef.nativeElement,
      this.popoverDropdownRef.nativeElement,
      {
        placement: "bottom-start",
      }
    );
  }

  onFileSelected(event: any): void {
    const file: File = event.target.files[0];
    this.membre.imageFile = file;
  }

  
  onEquipeSelectionChange(selectedEquipe: string): void {
    this.selectedEquipeName = selectedEquipe;
  }

  createMembre(): void {
    // Retrieve the selected equipe ID based on the name
    const selectedEquipeId = this.getSelectedEquipeId(this.selectedEquipeName);
    if (selectedEquipeId !== null) {
      this.membre.equipeInterventionId = selectedEquipeId;
      // Call the service method to create a member
      this.membreService.createMembreWithImage(this.membre).subscribe(
        (createdMembre: Membre) => {
          console.log('New member created:', createdMembre);
          //alert('New team was created!');
          this.showAlert = true;
        },
        (error: any) => {
          console.error('Error creating member:', error);
        }
      );
    } else {
      console.error('Selected equipe not found.');
    }
  }

  closeAlert(): void {
    this.showAlert = false; 
  }

  private getSelectedEquipeId(equipeName: string): number | null {
    const index = this.equipeNames.indexOf(equipeName);
    if (index !== -1) {
      // Assuming IDs start from 1
      return index + 1;
    } else {
      return null;
    }
  }
}

