db.createUser(
  {
    user: "sobreiro",
    pwd: "123456",
    roles: [
      {
        role: "readWrite",
        db: "shoppingcart"
      }
    ]
  }
);
db.createCollection('items');
db.items.insertMany([
    {
      _id: new ObjectId("6346bfa01c3d0be23964e84d"),
      name: "T-shirt",
      price:"12.99"
    },
    {
      _id: new ObjectId("6346bfa01c3d0be23964e84e"),
      name: "Jeans",
      price: "25.00"
    },
    {
      _id: new ObjectId("6346bfa01c3d0be23964e84f"),
      name: "Dress",
      price: "20.65"
    }
  ]
);
db.createCollection('carts');
db.carts.insertMany([
  {_id: new ObjectId("6346d76fc2a561172dbc67d8"),documentNumber:"12342145632","items":[{_id:"6346bfa01c3d0be23964e84d",name:"T-shirt",price:"12.99"}],_class:"com.api.shoppingcart.domain.entities.Cart"},
  {_id: new ObjectId("6346d96431dd9b738aa27555"),documentNumber:"12342145632","items":[{_id:"6346bfa01c3d0be23964e84d",name:"T-shirt",price:"12.99"},{_id:"6346bfa01c3d0be23964e84d",name:"T-shirt",price:"12.99"}],_class:"com.api.shoppingcart.domain.entities.Cart"},
  {_id: new ObjectId("6346da65062b2a61ad75dc28"),documentNumber:"12342145632","items":[{_id:"6346bfa01c3d0be23964e84d",name:"T-shirt",price:"12.99"},{_id:"6346bfa01c3d0be23964e84d",name:"T-shirt",price:"12.99"},{_id:"6346bfa01c3d0be23964e84d",name:"T-shirt",price:"12.99"}],_class:"com.api.shoppingcart.domain.entities.Cart"},
  {_id: new ObjectId("6346f017a7c953374505c5b0"),documentNumber:"12342145632","items":[{_id:"6346bfa01c3d0be23964e84d",name:"T-shirt",price:"12.99"},{_id:"6346bfa01c3d0be23964e84d",name:"T-shirt",price:"12.99"},{_id:"6346bfa01c3d0be23964e84d",name:"T-shirt",price:"12.99"},{_id:"6346bfa01c3d0be23964e84d",name:"T-shirt",price:"12.99"}],_class:"com.api.shoppingcart.domain.entities.Cart"},
  {_id: new ObjectId("6346f079a7c953374505c5b1"),documentNumber:"12342145632","items":[{_id:"6346bfa01c3d0be23964e84d",name:"T-shirt",price:"12.99"},{_id:"6346bfa01c3d0be23964e84d",name:"T-shirt",price:"12.99"},{_id:"6346bfa01c3d0be23964e84e",name:"Jeans",price:"25.00"},{_id:"6346bfa01c3d0be23964e84e",name:"Jeans",price:"25.00"}],_class:"com.api.shoppingcart.domain.entities.Cart"},
  {_id: new ObjectId("6346f120a7c953374505c5b2"),documentNumber:"12342145632","items":[{_id:"6346bfa01c3d0be23964e84d",name:"T-shirt",price:"12.99"},{_id:"6346bfa01c3d0be23964e84e",name:"Jeans",price:"25.00"},{_id:"6346bfa01c3d0be23964e84e",name:"Jeans",price:"25.00"},{_id:"6346bfa01c3d0be23964e84e",name:"Jeans",price:"25.00"},{_id:"6346bfa01c3d0be23964e84f",name:"Dress",price:"20.65"},{_id:"6346bfa01c3d0be23964e84f",name:"Dress",price:"20.65"},{_id:"6346bfa01c3d0be23964e84f",name:"Dress",price:"20.65"}],_class:"com.api.shoppingcart.domain.entities.Cart"},
  {_id: new ObjectId("6346f1f0a7c953374505c5b3"),documentNumber:"12342145632","items":[{_id:"6346bfa01c3d0be23964e84f",name:"Dress",price:"20.65"},{_id:"6346bfa01c3d0be23964e84f",name:"Dress",price:"20.65"},{_id:"6346bfa01c3d0be23964e84f",name:"Dress",price:"20.65"},{_id:"6346bfa01c3d0be23964e84e",name:"Jeans",price:"25.00"},{_id:"6346bfa01c3d0be23964e84e",name:"Jeans",price:"25.00"},{_id:"6346bfa01c3d0be23964e84d",name:"T-shirt",price:"12.99"}],_class:"com.api.shoppingcart.domain.entities.Cart"}
]
);