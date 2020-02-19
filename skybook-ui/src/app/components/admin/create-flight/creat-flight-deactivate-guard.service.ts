import { CanDeactivate } from '@angular/router';
import { Injectable } from '@angular/core';
import { CreateFlightComponent } from './create-flight.component';

@Injectable()
export class CreateFlightCanDeactivateGuardService implements CanDeactivate<CreateFlightComponent>
{
    canDeactivate(component : CreateFlightComponent) : boolean
    {
        if(component.addFlightForm.dirty)
        {
            return confirm('Are you sure you want to discard your changes ?');
        }

        return true;
    }
}