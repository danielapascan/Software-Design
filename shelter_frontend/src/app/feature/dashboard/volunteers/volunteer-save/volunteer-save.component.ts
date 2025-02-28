import { Component } from '@angular/core';
import { VolunteerService } from '../../../../core/service/volunteer/volunteer.service';
import { Router } from '@angular/router';
import { VolunteerModel } from '../../../../shared/models/volunteer.model';
import { PopupService } from '../../../../shared/components/popup/popup.service';

@Component({
  selector: 'app-volunteer-save',
  templateUrl: './volunteer-save.component.html',
  styleUrl: './volunteer-save.component.css',
})
export class VolunteerSaveComponent {
  volunteerData: VolunteerModel = {
    id: '',
    email: '',
    password: '',
    role: 'VOLUNTEER',
    volunteeringStartingDate: '',
  };

  constructor(
    private volunteerService: VolunteerService,
    private router: Router,
    private popupService: PopupService
  ) {}

  onSubmit(volunteerData: VolunteerModel): void {
    console.log(volunteerData);
    this.volunteerService.saveVolunteer(volunteerData).subscribe(
      (response) => {
        this.router.navigate(['/dashboard/volunteers']);
      },
      (error) => {
        this.popupService.openPopup('The data you introduced is not valid!');
      }
    );
  }
}
