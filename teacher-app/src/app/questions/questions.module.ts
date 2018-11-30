import { NgModule, NO_ERRORS_SCHEMA } from "@angular/core";
import { NativeScriptCommonModule } from "nativescript-angular/common";

import { QuestionsRoutingModule } from "~/app/questions/questions-routing.module";
import { QuestionsComponent } from "~/app/questions/questions.component";

@NgModule({
    imports: [
        NativeScriptCommonModule,
        QuestionsRoutingModule
    ],
    declarations: [
        QuestionsComponent
    ],
    schemas: [
        NO_ERRORS_SCHEMA
    ]
})
export class QuestionsModule { }
