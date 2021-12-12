const { Router } = require('express');
const express = require('express');
const router = express.Router();

const pool = require('../database.js');
const { route } = require('./productos.js');


router.get('/',async(req, res) =>{
    let listproducts = await pool.query('SELECT * FROM productostienda');
    res.json({
        status:200,
        message:"Listado Correctamente! :)",
        products:listproducts

    });
});

router.post('/create',async(req,res)=>{
    const {name,descripcion,categoria,cantidad,codigo} = req.body;
    const createProduct = {
        name,descripcion,categoria,cantidad,codigo
    };
    await pool.query('INSERT INTO productostienda SET ?',[createProduct]);
    res.json({
        status:200,
        message:"Creado Correctamente :)",
        products:createProduct
    });
});



module.exports = router;