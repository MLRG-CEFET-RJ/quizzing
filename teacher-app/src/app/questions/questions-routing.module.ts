import { NgModule } from "@angular/core";
import { Routes } from "@angular/router";
import { NativeScriptRouterModule } from "nativescript-angular/router";

import { QuestionsComponent } from "~/app/questions/questions.component";

const routes: Routes = [
    { path: "", component: QuestionsComponent }
];

@NgModule({
    imports: [NativeScriptRouterModule.forChild(routes)],
    exports: [NativeScriptRouterModule]
})
export class QuestionsRoutingModule { }
