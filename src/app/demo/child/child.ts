import { Component, EventEmitter, Input, Output } from '@angular/core';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-child',
  standalone: true,
  imports: [FormsModule], 
  template: `
    <div class="p-3 border rounded bg-light text-dark">
      <h4>Dete komponenta</h4>
      <p>Primljen podatak od roditelja: <strong>{{ item }}</strong></p>

      <ng-content></ng-content> 
    </div>
  `
})
export class ChildComponent {
  @Input() item = ''; 

  newItem = '';
}
