import { TestBed } from '@angular/core/testing';

import { TokenExpirationInterceptor } from './token-expiration.interceptor';

describe('TokenExpirationInterceptor', () => {
  beforeEach(() => TestBed.configureTestingModule({
    providers: [
      TokenExpirationInterceptor
      ]
  }));

  it('should be created', () => {
    const interceptor: TokenExpirationInterceptor = TestBed.inject(TokenExpirationInterceptor);
    expect(interceptor).toBeTruthy();
  });
});
