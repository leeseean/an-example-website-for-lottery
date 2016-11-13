import {Component, Input} from '@angular/core';
import {Hero} from 'commom/hero';

@Component({
    selector: 'hero-detail',
    templateUrl: './hero-detail.component.html',
})
export class HeroDetailComponent {
        @Input()
        hero: Hero
}