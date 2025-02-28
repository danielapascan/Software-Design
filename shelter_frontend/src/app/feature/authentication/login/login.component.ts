import { Component, DestroyRef, OnInit } from '@angular/core';
import { takeUntilDestroyed } from '@angular/core/rxjs-interop';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthService } from '../../../core/service/auth/auth.service';
import { PersonService } from '../../../core/service/person/person.service';
import { LoginModel } from '../../../shared/models/login.model';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrl: './login.component.css'
})
export class LoginComponent implements OnInit {

  loginForm: FormGroup = new FormGroup({});
  errorMessage?: string;

  constructor(
    private formBuilder: FormBuilder,
    private authService: AuthService,
    private personService: PersonService,
    private router: Router,
    private destroyRef: DestroyRef
  ) {
  }

  ngOnInit(): void {
    this.buildLoginForm();
  }

  login(): void {
    if (!this.loginForm?.valid) {
      this.errorMessage = 'Invalid form completion!';
      setTimeout(() => this.errorMessage = undefined, 3000);
      return;
    }

    const credentials: LoginModel = {
      email: this.loginForm?.get('email')?.value,
      password: this.loginForm?.get('password')?.value
    };
    this.authService.login(credentials)
      .pipe(takeUntilDestroyed(this.destroyRef))
      .subscribe({
        next: () => this.getPersonInfo(),
        error: () => this.errorMessage = 'Invalid credentials'
      });
  }

  private buildLoginForm(): void {
    this.loginForm = this.formBuilder.group({
      email: [ '', [ Validators.required, Validators.email ] ],
      password: [ '', [ Validators.required ] ]
    });
  }

  private getPersonInfo(): void {
    this.personService.getInfo()
      .pipe(takeUntilDestroyed(this.destroyRef))
      .subscribe(response => {
        localStorage.setItem('loggedPerson', JSON.stringify(response));
        this.router.navigateByUrl('/dashboard/animals');
      });
  }
}
