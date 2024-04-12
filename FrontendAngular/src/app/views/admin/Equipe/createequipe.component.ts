import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { EquipeService } from '../../../../core/service/EquipeService';
import { Equipe } from '../../../../core/models/Equipe';

@Component({
  selector: 'app-createequipe',
  templateUrl: './createequipe.component.html',
})
export class CreateEquipeComponent implements OnInit {
  showAlert = false;
  equipeForm: FormGroup;
  equipe: Equipe = {
    nomEquipe: '',
    description: ''
  };

  constructor(private formBuilder: FormBuilder, private equipeService: EquipeService) {}

  ngOnInit(): void {
    // Initialize the form group and define its structure
    this.equipeForm = this.formBuilder.group({
      nomEquipe: ['', Validators.required],
      description: ['', Validators.required]
    });
  }

  onSubmit() {
    console.log('Submitting form...');
    if (this.equipeForm.valid) {
      console.log('Form is valid. Proceeding with submission...');
      console.log('Form values:', this.equipeForm.value);
      
      // Call the service method to create a new equipe
      this.equipeService.createEquipe(this.equipeForm.value).subscribe(
        (response) => {
          console.log('Equipe created successfully:', response);
          this.showAlert = true;
         
        },
        (error) => {
          console.error('Error creating Equipe:', error);

        }
      );

    } else {
      console.log('Form is invalid. Cannot submit.');
      
    }
  }

  closeAlert(): void {
    this.showAlert = false; 
  }
}
