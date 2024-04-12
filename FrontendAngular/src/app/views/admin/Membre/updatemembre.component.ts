import { Component, OnInit } from "@angular/core";
import { ActivatedRoute, Router } from "@angular/router";
import { Membre } from "../../../../core/models/Membre";
import { MembreService } from "../../../../core/service/MembreService";
import { FormBuilder, FormGroup, Validators } from "@angular/forms";


@Component({
  selector: 'app-updatemembre',
  templateUrl: './updatemembre.component.html'
})
export class UpdateMembreComponent implements OnInit {


  ngOnInit(): void {
  }
}

