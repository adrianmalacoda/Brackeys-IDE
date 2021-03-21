/*
 * Copyright 2021 Brackeys IDE contributors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.brackeys.ui.internal.di.app

import android.content.Context
import android.os.Environment
import com.brackeys.ui.data.delegate.DatabaseDelegate
import com.brackeys.ui.data.delegate.FilesystemDelegate
import com.brackeys.ui.data.storage.database.AppDatabase
import com.brackeys.ui.filesystem.base.Filesystem
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Named
import javax.inject.Singleton
import java.io.File

@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase {
        return DatabaseDelegate.provideAppDatabase(context)
    }

    @Provides
    @Singleton
    @Named("Local")
    fun provideLocalFilesystem(): Filesystem {
        return FilesystemDelegate.provideFilesystem(new File("/"))
    }

    @Provides
    @Singleton
    @Named("Cache")
    fun provideCacheFilesystem(@ApplicationContext context: Context): Filesystem {
        return FilesystemDelegate.provideFilesystem(context.filesDir)
    }
}
