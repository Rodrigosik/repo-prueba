import 'reflect-metadata';

export class Features {
  cleaner(input?: any): this {
    const props = Object.getOwnPropertyNames(this);
    const propsInput = Object.keys(input);
    propsInput.forEach(propInput => {
      if (!props.includes(propInput)) {
        delete input[propInput];
      }
      if (input[propInput] === '') {
        input[propInput] = null;
      }
    });

    Object.assign(this, input);
    return this;
  }
}
