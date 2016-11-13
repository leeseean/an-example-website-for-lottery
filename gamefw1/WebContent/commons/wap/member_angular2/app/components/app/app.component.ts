import {Component} from '@angular/core';
import {Hero} from 'common/hero';
import {AppState} from 'common/app-state';

@Component({
  selector: 'member-app',
  templateUrl: './app.component.html'
})
export class AppComponent {
    appState: AppState = {
        version: "1.0.1",
        start_time: 0,
        finish_time: 100
    }
    selectedHero: Hero
    heros: Hero[] = [{id: 1, name: 'jackey', age: 28}, {id: 2, name: 'Anna', age: 28}]
    heroSelected(hero: Hero): void {
        this.selectedHero = hero;
    }
};