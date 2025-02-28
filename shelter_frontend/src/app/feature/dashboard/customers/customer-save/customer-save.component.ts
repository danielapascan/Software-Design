import { Component } from '@angular/core';
import { CustomerService } from '../../../../core/service/customer/customer.service';
import { Router } from '@angular/router';
import { CustomerModel } from '../../../../shared/models/customer.model';
import { PopupService } from '../../../../shared/components/popup/popup.service';
import { DonationModel } from '../../../../shared/models/donation.model';

@Component({
  selector: 'app-customer-save',
  templateUrl: './customer-save.component.html',
  styleUrl: './customer-save.component.css',
})
export class CustomerSaveComponent {
  customerData: CustomerModel = {
    id: '',
    email: '',
    password: '',
    role: 'CUSTOMER',
    donations: [],
    adoptions: [],
  };

  constructor(
    private customerService: CustomerService,
    private router: Router,
    private popupService: PopupService
  ) {}

  onSubmit(customerData: CustomerModel): void {
    console.log(customerData);
    this.customerService.saveCustomer(customerData).subscribe(
      (response) => {
        this.router.navigate(['/dashboard/customers']);
      },
      (error) => {
        this.popupService.openPopup('The data you introduced is not valid!');
      }
    );
  }
}
