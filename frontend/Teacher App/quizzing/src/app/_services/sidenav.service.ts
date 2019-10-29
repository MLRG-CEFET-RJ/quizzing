import {Injectable} from '@angular/core';
import {MatSidenav} from '@angular/material';

@Injectable()
export class SidenavService
{
  private sidenav: MatSidenav;

  public setSidenav(sidenav: MatSidenav)
  {
    this.sidenav = sidenav;
  }

  public open(): void
  {
    this.sidenav.open();
  }

  public close(): void
  {
    this.sidenav.close();
  }

  public toggle(isOpen?: boolean): void
  {
    this.sidenav.toggle(isOpen);
  }
}
