export interface BookingResponseDTO {
    bookingId: string;
    roomNumber: number;
    email: string;
    startDate: string;
    endDate: string;
    numberOfGuests: number;
    isPayed: boolean;
    paymentMethod: 'NOT_SELECTED' | 'CASH' | 'CREDIT_CARD' | 'DEBIT_CARD' | 'PAYPAL' | string;
}

export interface CustomerResponseDTO {
    id: string;
    firstName: string;
    lastName: string;
    email: string;
    address: string;
    birthDate: string;
}

export interface RoomResponseDTO {
    roomId: string;
    numberOfPerson: number;
    roomNumber: number;
    roomPrice: number;
    roomType: string;
    withBalcony: boolean;
    description: string;
}