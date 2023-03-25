package com.fawry.TicketsMall.angularAutomation.backendServices;

import com.fawry.TicketsMall.angularAutomation.backendServices.database.services.*;
import com.fawry.TicketsMall.angularAutomation.dataModels.CategoryDM;
import com.fawry.TicketsMall.angularAutomation.utils.Log;

import java.util.ArrayList;
import java.util.List;

public class ServicesDelegate {

    public ServicesDelegate()
    {
        Log.info(" *******   Create an instance of ServiceDelegate to access backend DB    ******** ");
    }



    public CategoryDM getCategoryDetails(String CategoryEnName)
    {
        CategoryDM CategoryDM= null;
        try {
            CategoryService CategoryService = new CategoryService();
            CategoryDM = CategoryService.getCategoryDetails(CategoryEnName);

        }

        catch (Exception e)
        {
            Log.error("ERROR occured in " + new Object() {}
                    .getClass().getName() + "." + new Object() {}
                    .getClass()
                    .getEnclosingMethod()
                    .getName(), e);
        }
        return CategoryDM;
    }


    public CategoryDM getCategoryStatus(String CategoryEnName)
    {
        CategoryDM CategoryDM= null;
        try {
            CategoryService CategoryService = new CategoryService();
            CategoryDM = CategoryService.getCategoryStatus(CategoryEnName);

        }

        catch (Exception e)
        {
            Log.error("ERROR occured in " + new Object() {}
                    .getClass().getName() + "." + new Object() {}
                    .getClass()
                    .getEnclosingMethod()
                    .getName(), e);
        }
        return CategoryDM;
    }

    public CategoryDM getDeletedCategory()
    {
        CategoryDM CategoryDM= null;
        try {
            CategoryService CategoryService = new CategoryService();
            CategoryDM = CategoryService.getDeletedCategory();

        }

        catch (Exception e)
        {
            Log.error("ERROR occured in " + new Object() {}
                    .getClass().getName() + "." + new Object() {}
                    .getClass()
                    .getEnclosingMethod()
                    .getName(), e);
        }
        return CategoryDM;
    }

    public CategoryDM getAllCategoryDetails()
    {
        CategoryDM CategoryDM= null;
        try {
            CategoryService CategoryService = new CategoryService();
            CategoryDM = CategoryService.getAllCategoryDetails();

        }

        catch (Exception e)
        {
            Log.error("ERROR occured in " + new Object() {}
                    .getClass().getName() + "." + new Object() {}
                    .getClass()
                    .getEnclosingMethod()
                    .getName(), e);
        }
        return CategoryDM;
    }

    public CategoryDM getCategoryEventsCount(String CategoryEnName)
    {
        CategoryDM CategoryDM= null;
        try {
            CategoryService CategoryService = new CategoryService();
            CategoryDM = CategoryService.getCategoryEventsCount(CategoryEnName);

        }

        catch (Exception e)
        {
            Log.error("ERROR occured in " + new Object() {}
                    .getClass().getName() + "." + new Object() {}
                    .getClass()
                    .getEnclosingMethod()
                    .getName(), e);
        }
        return CategoryDM;
    }
}
