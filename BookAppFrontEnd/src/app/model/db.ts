export class Fav{
    favUrl:string="";
    
    constructor(url:string)
    {
        this.favUrl=url;
    }
    get favUrls(): string {
        return this.favUrl;
    }
}