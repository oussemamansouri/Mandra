import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-guast-house',
  templateUrl: './guast-house.component.html',
  styleUrls: ['./guast-house.component.css']
})
export class GuastHouseComponent implements OnInit {
  guastHouses = [
    {
      id: 1,
      title: 'Dar Beya',
      img: 'darBeya.jpg',
      region: 'Béja',
      description: "Dar Beya est un magnifique endroit, c'est un éco-dome, conçu pour offrir une expérience de séjour unique et durable",
      category: 'hotel de charme ',
      rooms: '7', 
      views: 'Jardin 🌳 Piscine 🏊 Montagne ⛰️',
      Wifi: 'Oui',
      Parking: 'Oui',
      Restaurant: 'Oui',
      Gym: 'Non',
      images: ['darBeya(1).jpeg', 'darBeya(2).jpeg', 'darBeya(3).jpg', 'darBeya(4).jpg'],
      created_at: '2024-06-30',
      updated_at: '2024-06-30',
    },
    {
      id: 2,
      title: 'Dar khadija',
      img: 'darkhadija.jpeg',
      images: ['darkhadija(1).jpeg', 'darkhadija(2).jpg', 'darkhadija(3).jpeg', 'darkhadija(4).jpeg'],
      region: 'Bou salem, Jendouba',
      description: 'Dar khadija une charmante maison d"hôte nichée au cœur de la nature, offrant une retraite paisible et revitalisante loin de l"agitation de la ville.Elle se trouve à proximité des sites archéologiques de Bulla Régia (à 20 minutes) et de Chemtou (à 1 heure). ',
      category: 'résidence rurale',
      rooms: '7',
      views: 'Jardin 🌳 Piscine 🏊 ',
      Wifi: 'Oui',
      Parking: 'Oui',
      Restaurant: 'Non',
      Gym: 'Non',
      created_at: '2023-02-01',
      updated_at: '2023-07-01'
    },
    {
      id: 3,
      title: 'Dar El Karma',
      img: 'darelkarma.jpg',
      images: ['darelkarma(1).jpg', 'darelkarma(2).jpeg', 'darelkarma(3).jpg', 'darelkarma(4).jpg'],
      region: 'Tabarka, Jendouba',
      description: ' Dar El Karma est une charmante maison dhôte nichée au cœur d"un cadre naturel apaisant, offrant une oasis de tranquillité loin du tumulte urbain. Entourée de paisibles forêts verdoyantes et de collines doucement vallonnées, cette résidence est l"endroit parfait pour retrouver un lien profond avec la nature. ',
      category: 'gîte',
      rooms: '10',
      views: 'Jardin 🌳 Piscine 🏊 ',
      Wifi: 'Oui',
      Parking: 'Oui',
      Restaurant: 'Oui',
      Gym: 'Non',
      created_at: '2023-02-01',
      updated_at: '2023-07-01'
    },
    {
      id: 4,
      title: 'Dar Saida',
      img: 'darsaida.jpg',
      images: ['darsaida(1).jpg', 'darsaida(2).jpg', 'darsaida(3).webp', 'darsaida(4).webp'],
      region: 'Tabarka, Jendouba',
      description: ' une maison d"hôtes située en plein centre-ville et au cœur de la médina du Kef,L"architecture ancienne et la décoration arabesque de DAR SAIDA vous transporteront dans le passé avec un confort haut de gamme. De plus, une terrasse panoramique offrant des vues imprenables sur toute la ville du Kef vous garantira un séjour idéal. ',
      rooms: '5',
      views: 'Ville 🏙️',
      Wifi: 'Oui',
      Parking: 'Oui',
      Restaurant: 'Non',
      Gym: 'Non',
      created_at: '2023-02-01',
      updated_at: '2023-07-01'
    },
    {
      id: 5,
      title: 'Dar Hlima',
      img: 'darhalima.png',
      images: ['darhalima(1).png', 'darhalima(2).png', 'darhalima(3).png', 'darhalima(4).jpg'],
      region: 'Kesra, Siliana',
      description: 'Une maison dhôte authentique vous accueille dans les pittoresques hauteurs du village berbère de Kesra, niché au cœur de la région de Siliana. Imprégnée de charme local et entourée par des paysages montagneux époustouflants',
      rooms: '3',
      views: 'Ville 🏙️ , Montagne 🌳 ',
      Wifi: 'Oui',
      Parking: 'Oui',
      Restaurant: 'Oui',
      Gym: 'Non',
      created_at: '2023-02-01',
      updated_at: '2023-07-01'
    },
    {
      id: 6,
      title: 'casa zitouna',
      img: 'casazitouna.jpg',
      images: ['casazitouna(1).jpg', 'casazitouna(2).jpg', 'casazitouna(3).jpg'],
      region: 'Le kef',
      description: ' une maison d"hôtes située au Kef, offrant un havre de tranquillité loin de l"agitation de la vie quotidienne.',
      rooms: '3',
      views: 'Jardin 🌳 ',
      Wifi: 'Oui',
      Parking: 'Oui',
      Restaurant: 'Non',
      Gym: 'Non',
      created_at: '2023-02-01',
      updated_at: '2023-07-01'
    },
    
  ];

  guastHouse: any = {};

  constructor() { }

  ngOnInit(): void {
  }

  openModal(guastHouse: any): void {
    this.guastHouse = guastHouse;
  }

  sendId(): void {
    // Code pour l'envoi de l'identifiant si nécessaire
  }

  deleteGuastHouse(): void {
    // Code pour supprimer la maison d'hôte si nécessaire
  }
}
