const { Router } = require('express');
const express = require('express');
const router = express.Router();

const pool = require('../database.js');
const { route } = require('./productos.js');

/// listar productos
router.get('/',async(req, res) =>{
    let listproducts = await pool.query('SELECT * FROM productostienda');
    res.json({
        status:200,
        message:"Listado Correctamente! :)",
        products:listproducts

    });
});
///Crear Producto
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

///Listar produtos por codigo de barras
router.get('/:codigo', async (req, res)=> {
    const { codigo } = req.params;
    let cod = await pool.query('SELECT * FROM productostienda WHERE codigo = ?', [codigo]);
    res.json({
        status: 200,
        message: "Se ha encontrado el producto",
        codigo:cod
    });
});

//Listar productos por categoria
router.get('/categoria/:categoria',async(req, res)=> {
    const { cat } = req.params;
    let quer = await pool.query('SELECT * FROM productostienda WHERE categoria = ?', [cat]);
    res.json({
        status: 200,
        message: "Se ha encontrado el producto por categoria",
        cat:quer
    });
});


module.exports = router;