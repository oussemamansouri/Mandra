import { BooleanToTextPipe } from './boolean-to-text.pipe';

describe('BooleanToTextPipe', () => {
  it('create an instance', () => {
    const pipe = new BooleanToTextPipe();
    expect(pipe).toBeTruthy();
  });
});
