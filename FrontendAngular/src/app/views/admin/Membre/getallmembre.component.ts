import { Component, OnInit } from "@angular/core";
import { Membre } from "../../../../core/models/Membre";
import { MembreService } from "../../../../core/service/MembreService";
import { Router } from '@angular/router';

@Component({
  selector: 'app-membregetall',
  templateUrl: './getallmembre.component.html'
})
export class MembregetallComponent implements OnInit {
  membres: Membre[] = [];
  color: string = "light";

  constructor(private membreService: MembreService, private router: Router) {}

  ngOnInit(): void {
    this.getAllMembres();
  }

  openUpdatePage(id: number) {
    this.router.navigate(['/admin/updatemembre', id]);
  }
  getAllMembres(): void {
    this.membreService.getAllMembres().subscribe(
      (membres: Membre[]) => {
        this.membres = membres;
        console.log("Membres fetched successfully:", this.membres);
      },
      (error: any) => {
        console.error('Error fetching membres:', error);
        // Handle error
      }
    );
  }

  deleteMembre(id: number): void {
    this.membreService.deleteMembre(id).subscribe(
      (response: any) => {
        if (response && response.message === 'success') {
          console.log('Member deleted successfully');
          // Refresh the list of members after deletion
          this.getAllMembres();
        } else {
          console.error('Error deleting member:', response);
          this.getAllMembres();
        }
      },
      (error: any) => {
        console.error('Error deleting member:', error);
        this.getAllMembres();
      }
    );
  }
  
  
}
