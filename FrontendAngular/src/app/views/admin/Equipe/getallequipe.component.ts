import { Component, OnInit, ViewChild, ElementRef } from "@angular/core";
import { Equipe } from "../../../../core/models/Equipe";
import { EquipeService } from "../../../../core/service/EquipeService";
import { DomSanitizer, SafeUrl } from '@angular/platform-browser';
import { Membre } from "../../../../core/models/Membre";
import { MembreService } from "../../../../core/service/MembreService";
import { Router } from '@angular/router';

@Component({
  selector: 'app-equipegetall',
  templateUrl: './getallequipe.component.html',
  styleUrls: ['./getallequipe.component.css']
})
export class EquipegetallComponent implements OnInit {
  equipes: Equipe[] = [];
  color: string = "light";

  @ViewChild('membreImages', { static: false }) membreImages: ElementRef;

  constructor(private equipeService: EquipeService, private membreService: MembreService , private router: Router ,private sanitizer: DomSanitizer) {}

  ngOnInit(): void {
    this.loadEquipes();
  }

  loadEquipes() {
    this.equipeService.getAllEquipes().subscribe((equipes) => {
      this.equipes = equipes;
      // Fetch members for each equipe
      this.fetchMembersForEquipes();
    });
  }

  openUpdatePage(equipeId: number) {
    this.router.navigate(['/admin/updateequipe', equipeId]);
  }
  
  fetchMembersForEquipes() {
    this.equipes.forEach(equipe => {
      this.equipeService.getMemberImagesByEquipeId(equipe.idEquipe).subscribe(images => {
        equipe.membres = images.map(image => this.convertBase64ToImageUrl(image));
      });
    });
  }

  convertBase64ToImageUrl(base64String: string): SafeUrl {
    const imageUrl = 'data:image/png;base64,' + base64String;
    return this.sanitizer.bypassSecurityTrustUrl(imageUrl);
  }

  fetchMembers() {
    this.equipes.forEach(equipe => {
      this.membreService.getMembersByEquipeId(equipe.idEquipe).subscribe(membres => {
        equipe.membres = membres;
      });
    });
  }
  
  deleteEquipe(id: number) {
    this.equipeService.deleteEquipe(id).subscribe(() => {
      // Remove the deleted equipe from the list
      this.equipes = this.equipes.filter(equipe => equipe.idEquipe !== id);
      this.loadEquipes();
    }, error => {
      console.error('Error deleting equipe:', error);
      this.loadEquipes();
    });
  }

  toggleTooltip(index: number) {
    if (!this.membreImages) return; 
    
    const images = this.membreImages.nativeElement.querySelectorAll('.membre-image');
    images.forEach((image, i) => {
      if (i === index) {
        image.classList.add('show-tooltip');
      } else {
        image.classList.remove('show-tooltip');
      }
    });
  }
}
