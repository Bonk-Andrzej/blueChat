import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { InformationSupportComponent } from './information-support.component';

describe('InformationSupportComponent', () => {
  let component: InformationSupportComponent;
  let fixture: ComponentFixture<InformationSupportComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ InformationSupportComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(InformationSupportComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
