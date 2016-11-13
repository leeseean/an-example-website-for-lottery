import {NgModule} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';
import {FormsModule} from '@angular/forms';

import {AppComponent} from './components/app/app.component';
import {HeroDetailComponent} from './components/herodetail/hero-detail.component';

@NgModule({
    imports: [
        BrowserModule, 
        FormsModule],
    declarations: [
        AppComponent, 
        HeroDetailComponent],
    bootstrap: [AppComponent], 
})
export class AppModule {
    
}

