/*M///////////////////////////////////////////////////////////////////////////////////////
//
//  IMPORTANT: READ BEFORE DOWNLOADING, COPYING, INSTALLING OR USING.
//
//  By downloading, copying, installing or using the software you agree to this license.
//  If you do not agree to this license, do not download, install,
//  copy or use the software.
//
//
//                          License Agreement
//                For Open Source Computer Vision Library
//
// Copyright (C) 2000-2008, Intel Corporation, all rights reserved.
// Copyright (C) 2009, Willow Garage Inc., all rights reserved.
// Copyright (C) 2013, OpenCV Foundation, all rights reserved.
// Third party copyrights are property of their respective owners.
//
// Redistribution and use in source and binary forms, with or without modification,
// are permitted provided that the following conditions are met:
//
//   * Redistribution's of source code must retain the above copyright notice,
//     this list of conditions and the following disclaimer.
//
//   * Redistribution's in binary form must reproduce the above copyright notice,
//     this list of conditions and the following disclaimer in the documentation
//     and/or other materials provided with the distribution.
//
//   * The name of the copyright holders may not be used to endorse or promote products
//     derived from this software without specific prior written permission.
//
// This software is provided by the copyright holders and contributors "as is" and
// any express or implied warranties, including, but not limited to, the implied
// warranties of merchantability and fitness for a particular purpose are disclaimed.
// In no event shall the Intel Corporation or contributors be liable for any direct,
// indirect, incidental, special, exemplary, or consequential damages
// (including, but not limited to, procurement of substitute goods or services;
// loss of use, data, or profits; or business interruption) however caused
// and on any theory of liability, whether in contract, strict liability,
// or tort (including negligence or otherwise) arising in any way out of
// the use of this software, even if advised of the possibility of such damage.
//
//M*/

#pragma once

#ifndef __OPENCV_CUDEV_PTR2D_TRAITS_HPP__
#define __OPENCV_CUDEV_PTR2D_TRAITS_HPP__

#include "../common.hpp"

namespace cv { namespace cudev {

//! @addtogroup cudev
//! @{

template <class Ptr2DSz, class Ptr2D> struct PtrTraitsBase
{
    typedef Ptr2DSz ptr_sz_type;
    typedef Ptr2D   ptr_type;

    typedef typename Ptr2D::value_type value_type;
    typedef typename Ptr2D::index_type index_type;

    __host__ static Ptr2D shrinkPtr(const Ptr2DSz& ptr)
    {
        return ptr;
    }

    __host__ static int getRows(const Ptr2DSz& ptr)
    {
        return ptr.rows;
    }

    __host__ static int getCols(const Ptr2DSz& ptr)
    {
        return ptr.cols;
    }
};

template <class Ptr2DSz> struct PtrTraits : PtrTraitsBase<Ptr2DSz, Ptr2DSz>
{
};

template <class Ptr2DSz>
__host__ typename PtrTraits<Ptr2DSz>::ptr_type shrinkPtr(const Ptr2DSz& ptr)
{
    return PtrTraits<Ptr2DSz>::shrinkPtr(ptr);
}

template <class Ptr2DSz>
__host__ int getRows(const Ptr2DSz& ptr)
{
    return PtrTraits<Ptr2DSz>::getRows(ptr);
}

template <class Ptr2DSz>
__host__ int getCols(const Ptr2DSz& ptr)
{
    return PtrTraits<Ptr2DSz>::getCols(ptr);
}

//! @}

}}

#endif
