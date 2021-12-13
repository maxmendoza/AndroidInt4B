const { Router } = require('express');
const express = require('express');
const router = express.Router();

const pool = require('../database.js');
const { route } = require('./categoria.js');

///Listar categorias
router.get('/',async(req, res) =>{
    let listcategory = await pool.query('SELECT * FROM categoriatienda');
    res.json({
        status:200,
        message:"Listado Correctamente! :)",
        products:listcategory

    });
});

//Listar productos por categoria
router.get('/:categoria', async (req, res)=> {
    const { cat } = req.params;
    let categoria = await pool.query('SELECT * FROM productostienda WHERE categoria = ?', [cat]);
    res.json({
        status: 200,
        message: "Se ha encontrado el producto",
        cat:cat
    });
});



module.exports = router;