import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { ChildComponent } from '../child/child';

@Component({
  selector: 'app-parent',
  standalone: true,
  imports: [FormsModule, ChildComponent],
  template: `
    <div class="container mt-4">
      <h2>Roditeljska komponenta</h2>

      <div class="mb-3">
        <label>Trenutni item:</label>
        <input [(ngModel)]="currentItem" class="form-control" />
      </div>

      <app-child [item]="currentItem" />
    </div>
  `
})
export class ParentComponent {
  currentItem = 'Početna vrednost';
}
