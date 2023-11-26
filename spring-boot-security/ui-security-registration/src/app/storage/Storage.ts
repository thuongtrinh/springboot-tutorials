// import { Mixed, TypeOf, Validation } from 'io-ts';
// import { Observable } from 'rxjs';

// export interface Storage {
//   storage: Storage.Service;
// }

// export namespace Storage {
//   export interface Service {
//     getItem: <Data extends Mixed>(key: string, data: Data) => ZIO<Any, never, Option<TypeOf<Data>>>;
//     setItem: <A>(key: string, value: A) => ZIO<Any, never, void>;
//     removeItem: (key: string) => ZIO<Any, never, void>;
//     clear: ZIO<Any, never, void>;
//   }
// }

// export abstract class AppStorage {
//   abstract getItem<C extends Mixed>(key: string, codec: C): Observable<Validation<TypeOf<C>>>;
//   abstract setItem<T>(key: string, value: T): Observable<T>;
//   abstract removeItem(key: string): Observable<void>;
//   abstract clear(): Observable<void>;
// }
