import * as CryptoJS from 'crypto-js';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
// Clave de encriptación (puede ser cualquier valor
export class encrypt{

  public encryptionKey:string;
  constructor(){
    this.encryptionKey= 'MyKeySecret';
  }

 encryptData(data: string): string {
  return CryptoJS.AES.encrypt(data, this.encryptionKey).toString();
  }

  decryptData(encryptedData: string): string {
    return CryptoJS.AES.decrypt(encryptedData, this.encryptionKey).toString(CryptoJS.enc.Utf8);
  }
}