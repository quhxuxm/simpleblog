import {FailPayloadType} from "./FailPayloadType";

export class FailPayload {
  constructor(public message: string, public type: FailPayloadType) {
  }
}
