import { Component, OnInit } from "@angular/core";
import { ActivatedRoute, Router } from "@angular/router";
import { Equipe } from "../../../../core/models/Equipe";
import { EquipeService } from "../../../../core/service/EquipeService";
import { FormBuilder, FormGroup, Validators } from "@angular/forms";
import { Disponibilite } from "../../../../core/models/Disponibilite";

@Component({
  selector: 'app-updateequipe',
  templateUrl: './updateequipe.component.html'
})
export class UpdateEquipeComponent implements OnInit {
  equipeId: number;
  equipe: Equipe;
  equipeForm: FormGroup;

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private equipeService: EquipeService,
    private formBuilder: FormBuilder
  ) {}

  ngOnInit(): void {
    // Get the equipe id from the route parameters
    this.equipeId = +this.route.snapshot.paramMap.get('id');

    // Initialize the form with empty values
    this.equipeForm = this.formBuilder.group({
      nomEquipe: ['', Validators.required],
      description: ['', Validators.required],
      disponibilite: [Disponibilite.Disponible, Validators.required]
    });

    // Fetch the existing equipe data using its id
    this.equipeService.getEquipeById(this.equipeId).subscribe(
      (equipe: Equipe) => {
        this.equipe = equipe;
        // Populate the form fields with existing data
        this.equipeForm.patchValue({
          nomEquipe: equipe.nomEquipe,
          description: equipe.description,
          disponibilite: equipe.disponibilite
        });
      },
      error => {
        console.error('Error fetching equipe:', error);
        // Handle error, such as redirecting to an error page
      }
    );
  }

  onSubmit(): void {
    if (this.equipeForm.invalid) {
      return;
    }
  
    // Get the updated data from the form
    let updatedEquipeData: Equipe = this.equipeForm.value;
  
    // Adjust the disponibilite value to match backend expectations
    updatedEquipeData = {
      ...updatedEquipeData,
      disponibilite: updatedEquipeData.disponibilite === 'Disponible' ? Disponibilite.Disponible : Disponibilite.NonDisponible
    };
  
    // Update the equipe using its id
    this.equipeService.updateEquipe(this.equipeId, updatedEquipeData).subscribe(
      () => {
        console.log('Equipe updated successfully');
        // Redirect to the equipe list page or any other page as needed
        this.router.navigate(['/admin/listequipe']);
      },
      error => {
        console.error('Error updating equipe:', error);
        // Handle error, such as displaying an error message to the user
      }
    );
  }
  
  
  
}
