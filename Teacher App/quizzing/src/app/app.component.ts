import {Component, OnInit, ViewChild} from '@angular/core';
import {MatSidenav} from '@angular/material';
import {SidenavService} from './_services/sidenav.service';
import {Router} from '@angular/router';

@Component({
             selector:    'app-root',
             templateUrl: './app.component.html',
             styleUrls:   ['./app.component.css'],
             providers:   [SidenavService]
           })
export class AppComponent implements OnInit
{
  title = 'Quizzing';

  @ViewChild('sidenav', {static: true}) public sidenav: MatSidenav;

  public constructor(private sidenavService: SidenavService,
                     private router: Router)
  {
    router.events.subscribe(event =>
                            {
                              // close sidenav on routing
                              this.sidenavService.close();
                            });
  }

  /**
   * OnInit life cycle hook
   */
  public ngOnInit(): void
  {
    // Store sidenav to service
    this.sidenavService.setSidenav(this.sidenav);
  }
}
