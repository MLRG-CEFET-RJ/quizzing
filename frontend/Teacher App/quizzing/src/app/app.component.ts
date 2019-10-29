import {Component, OnInit, ViewChild} from '@angular/core';
import {MatSidenav} from '@angular/material';
import {SidenavService} from './_services/sidenav.service';
import {Router} from '@angular/router';
import {AuthenticationService} from './_services/authentication.service';
import {User} from './user/user.model';

@Component({
             selector:    'app-root',
             templateUrl: './app.component.html',
             styleUrls:   ['./app.component.css'],
             providers:   [SidenavService]
           })
export class AppComponent implements OnInit
{
  title = 'Quizzing';
  currentUser: User;

  @ViewChild('sidenav', {static: true}) public sidenav: MatSidenav;

  public constructor(private sidenavService: SidenavService,
                     private authenticationService: AuthenticationService,
                     private router: Router)
  {
    router.events.subscribe(event =>
                            {
                              // close sidenav on routing
                              this.sidenavService.close();
                            });
    this.authenticationService.currentUser.subscribe(x => this.currentUser = x);
  }

  /**
   * OnInit life cycle hook
   */
  public ngOnInit(): void
  {
    // Store sidenav to service
    this.sidenavService.setSidenav(this.sidenav);
  }

  logout()
  {
    this.sidenavService.toggle();
    this.authenticationService.logout();
    this.router.navigate(['/login']);
  }
}
