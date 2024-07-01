// boolean-to-text.pipe.ts
import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'booleanToText'
})
export class BooleanToTextPipe implements PipeTransform {

  transform(value: boolean, trueText: string = 'Oui', falseText: string = 'Non'): string {
    return value ? trueText : falseText;
  }

}
