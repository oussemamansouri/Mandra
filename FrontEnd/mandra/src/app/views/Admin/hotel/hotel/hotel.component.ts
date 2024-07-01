import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-hotel',
  templateUrl: './hotel.component.html',
  styleUrls: ['./hotel.component.css']
})
export class HotelComponent implements OnInit {
  hotels = [
    {
      id: 1,
      title: 'Hotel Al-Rawabi',
      img: 'alrawabi-hotel (4).jpg',
      region: 'Nefza, Béja',
      description: "Al Rawabi est un hôtel classé 3 étoiles situé dans la magnifique ville de Nefza",
      category: 'hotel de charme ',
      rooms : '30', 
      views: 'Jardin 🌳 Piscine 🏊 Montagne ⛰️',
      accomodation: 'Chambre single, double, triple 🛏️',
      Wifi: 'Oui',
      Parking:'Oui',
      Restaurant: 'Oui',
      Gym: 'Non',
      images: ['alrawabi-hotel (1).jpg', 'alrawabi-hotel (3).jpg','alrawabi-hotel (5).jpg','alrawabi-hotel (2).jpg'],
      created_at: '2024-06-30',
      updated_at: '2024-06-30',
    },
    {
      id: 2,
      title: 'Hotel Relais le Mersaillais',
      img: 'Hotel-Relais.jpg',
      region: 'Nefza, Béja',
      description: 'Relais le marseillais  , où vous profiterez de vues magnifiques sur la nature, de calme absolu, et de délicieux repas préparés avec des ingrédients locaux. Un lieu idéal pour se ressourcer.',
      category: 'hotel de luxe',
      rooms : '15',
      views: 'Montagne 🌳',
      accomodation: 'Chambre single, double 🛏️',
      Wifi: 'Oui',
      Parking:'Oui',
      Restaurant: 'Oui',
      Gym: 'Non',
      images: ['Hotel-Relais (2).jpg', 'hotel-Relais (4).jpg','hotel-Relais(5).jpg','hotel-Relais(6).jpg'],
      created_at: '2024-06-30',
      updated_at: '2024-06-30',
    },
    {
      id: 3,
      title: 'Hotel dar ismail Nour el ain ',
      img: 'darIsmail.jpg',
      region: 'Ain Drahem, Jendouba',
      description: ' un établissement moderne classé 4 étoiles, propose une expérience de séjour exceptionnelle, il vous accueille dans un cadre unique ou règne calme et une ambiance conviviale et chaleureuse. ',
      category: 'hotel moderne',
      rooms : '64',
      views: 'Ville 🏙️ Montagne ⛰️',
      accomodation: 'Chambre single, double, triple 🛏️, suites  🏨',
      Wifi: 'Oui',
      Parking:'Oui',
      Restaurant: 'Oui',
      Gym:'Oui',
      images: ['DarIsmail(1).jpg', 'DarIsmail(2).jpg','DarIsmail(3).jpg','DarIsmail(4).png','DarIsmail(5).jpg'],
      created_at: '2024-06-30',
      updated_at: '2024-06-30',
     
    },
    {
      id: 4,
      title: 'Hotel Mouradi hammem Bourguiba ',
      img: 'hotel-Mouradi.jpg',
      region: 'Hammem Bourguiba, Jendouba',
      description: 'une résidence 4 étoiles, c"est un refuge de tranquillité avec une vue imprenable sur la nature.Profitez de spa et des installations de relaxation pour une expérience revitalisante. Un cadre idéal pour se ressourcer et se détendre ',
      category: 'hotel moderne',
      rooms : '145',
      views: 'Montagne ⛰️ Piscine 🏊',
      accomodation: 'Chambre single, double, triple,Quadruple 🛏️',
      Wifi: 'Oui',
      Parking:'Oui',
      Restaurant: 'Oui',
      Gym:'Oui',
      images: ['hotel-Mouradi(1).jpg', 'hotel-Mouradi(2).jpg', 'hotel-Mouradi(3).jpg','hotel-Mouradi(4).jpg'],
      created_at: '2024-06-30',
      updated_at: '2024-06-30',
    
    },
    {
      id: 5,
      title: 'Hotel Les Pins ',
      img: 'kd6r5ljy.webp',
      region: 'Le kef',
      description: 'Un hôtel moderne situé en plein centre-ville du kef, tranquille, calme' ,
      category: 'hotel moderne',
      rooms : '50',
      views: 'Ville 🏙️ ,Montagne ⛰️ Piscine 🏊',
      accomodation: 'Chambre single, double, triple 🛏️',
      Wifi: 'Oui',
      Parking:'Non',
      Restaurant: 'Oui',
      Gym:'Non',
      images: ['hotel-Pins.jpg', 'hotel-les-pins (1).jpg', 'hotel-Pins(2).jpg','hotel-Pins(3).jpg'],
      created_at: '2024-06-30',
      updated_at: '2024-06-30',
    
    },
    {
      id: 6,
      title: 'Hotel leklil ',
      img: 'hotel-leklil.jpg',
      region: 'Le kef',
      description: 'L’Hôtel LEKLIL est situé à l"entrée de la ville du KEF sur une colline au milieu de la forêt de pins dominant la"Table de JUGHURTA" ',
      category: 'hotel moderne',
      rooms : '18',
      views: 'Ville 🏙️ ,Montagne ⛰️ Piscine 🏊',
      accomodation: 'Chambre single, double, triple 🛏️',
      Wifi: 'Oui',
      Parking:'Oui',
      Restaurant: 'Oui',
      Gym:'Non',
      images: ['hotel-leklil(1).jpg', 'hotel-leklil(2).jpg', 'hotel-leklil(3).jpg','hotel-leklil(4).jpg'],
      created_at: '2024-06-30',
      updated_at: '2024-06-30',
    
    },
  ];

  hotel: any = {};

  constructor() { }

  ngOnInit(): void {
  }

  openModal(hotel: any): void {
    this.hotel = hotel;
  }

  sendId(): void {
    // Code pour l'envoi de l'identifiant si nécessaire
  }

  deleteHotel(): void {
    // Code pour supprimer l'hôtel si nécessaire
  }
}
